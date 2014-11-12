#!/usr/bin/env python

import csv
import re
import os
import sys
from tempfile import NamedTemporaryFile
import shutil
import subprocess

# USAGE: python insert_ints.py <genus> <input_file>

overwrite = True
input_file = sys.argv[2]
genus = sys.argv[1]
tempfile = NamedTemporaryFile(delete=not overwrite)

with open(input_file, 'rb') as ifile:
    dr = csv.DictReader(ifile,delimiter=",")
    dw = csv.DictWriter(tempfile,dr.fieldnames,delimiter=",")
    headers = {}
    for n in dw.fieldnames:
        headers[n] = n
    dw.writerow(headers)
    for row in dr:
        w = row['Word']
        if ( row['Intersection_Number'] == '-' ):
            cmd = 'java intersection_num '+genus+' '+w
            i = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE).stdout.read().strip()
            row['Intersection_Number']=i
            
        dw.writerow(row)
   
    tempfile.close()
    if overwrite:
        shutil.move(tempfile.name, input_file)
