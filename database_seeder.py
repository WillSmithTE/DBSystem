import pandas as pd  
import numpy as np 
import time, re, json 
import mysql.connector
from faker import Faker
from random import randint

# DB Connection
connection = mysql.connector.connect(host="charity-db.cp7zsxa1pi0j.ap-southeast-2.rds.amazonaws.com ",user="master", passwd="fRSnoXZ7139N", database="dev" )
c = connection.cursor()

fake = Faker()

locations_csv = r'C:\Users\patri\Desktop\locations_SS1A.csv'
df_locations = pd.read_csv(locations_csv)

def seedDonor():
	for i in range(500):
		try:
			insert_sql = (
				"""INSERT INTO `donor` (name, contact_number, email, email_confirmed, password) 
				VALUES (%s,%s,%s,%s,%s);"""
			)
		   
			data = (fake.name(), str(fake.phone_number()), (fake.last_name_male() + "@test.com.au"), 1, "A*^&$GD!&IHD")
			c.execute(insert_sql, data)
		except Exception as e:
			print(e)
		connection.commit()

def seedCharity():
	for i in range(500):
		try:
			insert_sql = (
				"""INSERT INTO `charity` (name, charity_description, contact_number, email, email_confirmed, charity_size, password) 
				VALUES (%s,%s,%s,%s,%s,%s,%s);"""
			)

			data = (fake.company(), fake.catch_phrase(), str(fake.phone_number()), (fake.last_name_female() + "@test.com.au"), int(1), int(randint(1,10000)), "A*^&$GD!&IHD")
			c.execute(insert_sql, data)
		except Exception as e:
			print(e)
		connection.commit()

def seedCharityListing():
	
	locations = df_locations['locations']
	for location in locations:
		try:
			insert_sql = (
				"""INSERT INTO `charity_listing` (listing_title, listing_description, location, charity_id, industry_id) 
				VALUES (%s,%s,%s,%s,%s);"""
			)

			listing_description_list = fake.paragraphs(nb=3, ext_word_list=None)
			listing_description = ''.join(listing_description_list)
		   
			data = (fake.job(), listing_description, location, int(randint(3643,3670)), int(randint(1,16)))
			c.execute(insert_sql, data)
		except Exception as e:
			print(e)
		connection.commit()

def main():
	# seedDonor()
	# seedCharity()
	seedCharityListing()

main()