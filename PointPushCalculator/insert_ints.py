#!/usr/bin/env python

import csv
import re
import os
import sys
from tempfile import NamedTemporaryFile
import shutil
import subprocess

# USAGE: python insert_ints.py <input_file>

overwrite = False
input_file = sys.argv[1]
#tempfile = NamedTemporaryFile(delete=not overwrite)
tempfile = NamedTemporaryFile(delete=False)
data_file = NamedTemporaryFile(delete=True)
head_file = NamedTemporaryFile(delete=True)

# Read the header information and the data information into 2 separate files
# (so that the data is formated for csv reader
with open(input_file, 'rb') as f:
    switch=False
    for line in f:
        if (switch == False):
            head_file.write(line)
        else: 
            data_file.write(line)
        if line == '\n':
            switch=True

data_file.seek(0)
head_file.seek(0)
for line in head_file:
    tempfile.write(line)

dr = csv.DictReader(data_file,delimiter=",")
dw = csv.DictWriter(tempfile,dr.fieldnames,delimiter=",")
headers = {}
for n in dw.fieldnames:
    headers[n] = n
dw.writerow(headers)
for row in dr:
    w = row['Word']
    genus = row['Genus']
#       if ( row['Intersection_Number'] == '-' ):
    cmd = 'java intersection_num '+genus+' '+w
    i = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE).stdout.read().strip()
    row['Intersection_Number']=i
    dw.writerow(row)

tempfile.close()
shutil.move(tempfile.name, input_file+'--withINT')

sys.exit()

tempfile.close()
if overwrite:
    shutil.move(tempfile.name, input_file)
else:
    shutil.move(tempfile.name, 'fixed--'+input_file)
