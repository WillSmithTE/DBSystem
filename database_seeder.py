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

locations_csv = r'locations_SS1A.csv'
df_locations = pd.read_csv(locations_csv)

def seedDonor():
	emails = ['@gmail.com', '@yahoo.com', '@hotmail.com', '@student.uts.edu.au']
	for i in range(1000):
		for email in emails:
			try:
				insert_sql = (
					"""INSERT INTO `donor` (name, contact_number, email, email_confirmed, password) 
					VALUES (%s,%s,%s,%s,%s);"""
				)
			
				data = (fake.name(), str(fake.phone_number()), (fake.last_name_male() + str(randint(1,100)) + str(email)), 1, "password")
				c.execute(insert_sql, data)
			except Exception as e:
				print(e)
			connection.commit()

def seedCharity():
	emails = ['@gmail.com', '@yahoo.com', '@hotmail.com', '@student.uts.edu.au']
	for i in range(1000):
		for email in emails:
			try:
				insert_sql = (
					"""INSERT INTO `charity` (name, charity_description, contact_number, email, email_confirmed, charity_size, password) 
					VALUES (%s,%s,%s,%s,%s,%s,%s);"""
				)

				data = (fake.company(), fake.catch_phrase(), str(fake.phone_number()), (fake.last_name_female() + str(randint(1,100)) + str(email)), int(1), int(randint(1,10000)), "password")
				c.execute(insert_sql, data)
			except Exception as e:
				print(e)
			connection.commit()

def seedCharityListing():
	
	locations= ['Lake Heights NSW 2502',
				'Berkeley NSW 2506',
				'Wongawilli NSW 2530',
				'Port Kembla NSW 2505',
				'Bowral NSW 2576'
			   ]
	for i in range(1000):
		for location in locations:
			try:
				insert_sql = (
					"""INSERT INTO `charity_listing` (listing_title, listing_description, location, charity_id, industry_id) 
					VALUES (%s,%s,%s,%s,%s);"""
				)

				listing_description_list = fake.paragraphs(nb=2, ext_word_list=None)
				listing_description = ''.join(listing_description_list)
			
				data = (fake.job(), listing_description, location, int(randint(6144,6148)), int(randint(1,16)))
				c.execute(insert_sql, data)
			except Exception as e:
				print(e)
			connection.commit()


def seedApplication():
	for i in range(1000):	
		try:
			insert_sql = (
				"""INSERT INTO `application` (donor_id, charity_listing_id, charity_id, cover_letter, contact_number,industry_id) 
				VALUES (%s,%s,%s,%s,%s,%s);"""
			)
			cv = "Please hire me for this role. my name is " + str(fake.name() +", and I think I am a suitable candidate for this charity service. Looking forward to hearing back from you.")
		
			data = (int(randint(7517,7550)), int(randint(12863,12990)), int(randint(6144,6160)), cv, str(fake.phone_number()), int(randint(1,16)))
			c.execute(insert_sql, data)
		except Exception as e:
			print(e)
		connection.commit()


def main():
	# seedDonor()
	# seedCharity()
	# seedCharityListing()
	# seedApplication()

main()
