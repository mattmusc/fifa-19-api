#!/usr/bin/env python3

import csv
import re
import sys


def clean(string):
  return string.replace('&', '&amp;')


def xmlScript(countries, clubs, players):
  sep = '\n'
  return '''
<?xml version="1.0" encoding="UTF-8"?>

<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd">

    <property dbms="oracle" value="false" name="autoIncrement"/>
    <property dbms="mysql" value="true" name="autoIncrement"/>

    <property name="now" value="sysdate" dbms="oracle"/>
    <property name="now" value="now()" dbms="mysql"/>
    <property name="now" value="now()" dbms="postgresql"/>

    <preConditions>
        <runningAs username="fifa19"/>
    </preConditions>

    <changeSet id="000-all-insert-country" author="mattmusc" context="dev">
        <comment>Insert some countries for testing</comment>        
        {}
        <rollback>
            <delete tableName="country"/>
        </rollback>
    </changeSet>

    <changeSet id="001-all-insert-club" author="mattmusc" context="dev">
        <comment>Insert some clubs for testing</comment>
        {}
        <rollback>
            <delete tableName="club"/>
        </rollback>
    </changeSet>

    <changeSet id="002-all-insert-player" author="mattmusc" context="dev">
        <comment>Insert some players for testing</comment>
        {}
        <rollback>
            <delete tableName="player"/>
        </rollback>
    </changeSet>

    <changeSet author="mattmusc" id="tag_version_0_0_1-d">
        <tagDatabase tag="version_0_0_1-d"/>
    </changeSet>

</databaseChangeLog>
'''.format(
  sep.join(countries), sep.join(clubs), sep.join(players)
)


def clubCsv2Xml(index, club_name, logo_url):
  club_name = clean(club_name)
  return f'''
\t\t\t\t<!-- club: line {index + 1} -->
\t\t\t\t<insert tableName="club">
\t\t\t\t\t<column name="name" type="nvarchar(50)"  value="{club_name}"/>
\t\t\t\t\t<column name="logo_url" type="varchar(255)"  value="{logo_url}"/>
\t\t\t\t</insert>
  '''


def countryCsv2Xml(index, country_name, flag_url):
  country_name = clean(country_name)
  return f'''
\t\t\t\t<!-- country: line {index + 1} -->
\t\t\t\t<insert tableName="country">
\t\t\t\t\t<column name="name" type="nvarchar(50)"  value="{country_name}"/>
\t\t\t\t\t<column name="flag_url" type="varchar(255)"  value="{flag_url}"/>
\t\t\t\t</insert>
  '''


def playerCsv2Xml(index, line, countries, clubs):
  number_re = re.compile('[^0-9.,]*')
  
  market_value = number_re.sub("", line["Value"])
  wage_value = number_re.sub("", line["Wage"])

  if len(line["Preferred Foot"]) > 1:
    pref_foot = line["Preferred Foot"][0]
  else:
    pref_foot = line["Preferred Foot"]
  
  return f'''
\t\t\t\t<!-- player: line {index + 1} -->
\t\t\t\t<insert tableName="player">
\t\t\t\t\t<column name="name" type="nvarchar(128)" value="{line["Name"]}"/>
\t\t\t\t\t<column name="age" type="int"  value="{line["Age"]}"/>
\t\t\t\t\t<column name="photo_url" type="varchar(255)"  value="{line["Photo"]}"/>
\t\t\t\t\t<column name="id_country" type="bigint"  value="{countries.index(line["Nationality"]) + 1}"/>
\t\t\t\t\t<column name="overall" type="int"  value="{line["Overall"]}"/>
\t\t\t\t\t<column name="id_club" type="bigint"  value="{clubs.index(line["Club"]) + 1}"/>
\t\t\t\t\t<column name="market_value" type="double"  value="{market_value}"/>
\t\t\t\t\t<column name="wage" type="double"  value="{wage_value}"/>
\t\t\t\t\t<column name="foot" type="varchar(10)"  value="{pref_foot}"/>
\t\t\t\t\t<column name="created_at" type="timestamp(6)" value="${{now}}"/>
\t\t\t\t\t<column name="updated_at" type="timestamp(6)" value="${{now}}"/>
\t\t\t\t</insert>
  '''


if __name__ == "__main__":

  if len(sys.argv) < 2:
    print("usage: csv2liquibase file.csv")
    sys.exit(0)
  
  lines = []
  with open(sys.argv[1], 'r') as csvfile:
    lines = [line for line in csv.DictReader(csvfile)]

  countries = {}
  for line in lines:
    country = line["Nationality"]
    url = line["Flag"]
    if country not in countries:
      countries[country] = url
  
  clubs = {}
  for line in lines:
    club = line["Club"]
    logo = line["Club Logo"]
    if club not in clubs:
      clubs[club] = logo
  
  countries_inserts = []
  for index, country_name in enumerate(sorted(countries.keys())):
    countries_inserts.append(countryCsv2Xml(index, country_name, countries[country_name]))
  
  clubs_inserts = []
  for index, club_name in enumerate(sorted(clubs.keys())):
    clubs_inserts.append(clubCsv2Xml(index, club_name, clubs[club_name]))

  countries_list = [k for k in sorted(countries.keys())]
  clubs_list = [k for k in sorted(clubs.keys())]

  players_inserts = []
  for index, line in enumerate(lines):
    players_inserts.append(playerCsv2Xml(index, line, countries_list, clubs_list))
  
  print(xmlScript(countries_inserts, clubs_inserts, players_inserts))
