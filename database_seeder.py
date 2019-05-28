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

locations_csv = r'/home/pyr0/StudioProjects/DBSystem2/DBSystem/locations_SS1A.csv'
df_locations = pd.read_csv(locations_csv)

def seedDonor():
	for i in range(5):
		try:
			insert_sql = (
				"""INSERT INTO `donor` (name, contact_number, email, email_confirmed, password) 
				VALUES (%s,%s,%s,%s,%s);"""
			)
		   
			data = (fake.name(), str(fake.phone_number()), (fake.last_name_male() + "@test.com.au"), 1, "password")
			c.execute(insert_sql, data)
		except Exception as e:
			print(e)
		connection.commit()

def seedCharity():
	for i in range(5):
		try:
			insert_sql = (
				"""INSERT INTO `charity` (name, charity_description, contact_number, email, email_confirmed, charity_size, password) 
				VALUES (%s,%s,%s,%s,%s,%s,%s);"""
			)

			data = (fake.company(), fake.catch_phrase(), str(fake.phone_number()), (fake.last_name_female() + "@test.com.au"), int(1), int(randint(1,10000)), "password")
			c.execute(insert_sql, data)
		except Exception as e:
			print(e)
		connection.commit()

def seedCharityListing():
	
	locations = df_locations['locations']
	for i in range(5):
		try:
			insert_sql = (
				"""INSERT INTO `charity_listing` (listing_title, listing_description, location, charity_id, industry_id) 
				VALUES (%s,%s,%s,%s,%s);"""
			)

			listing_description_list = fake.paragraphs(nb=3, ext_word_list=None)
			listing_description = ''.join(listing_description_list)
		   
<<<<<<< HEAD
			data = (fake.job(), listing_description, location, int(randint(5144,5200)), int(randint(1,16)))
=======
			data = (fake.job(), listing_description, location, int(randint(5645,6140)), int(randint(1,16)))
>>>>>>> 3f9c570064914736ac8d9b11743915da6cc16965
			c.execute(insert_sql, data)
		except Exception as e:
			print(e)
		connection.commit()

def main():
	seedDonor()
	seedCharity()
	seedCharityListing()

main()
