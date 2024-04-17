import os
import zipfile

# get all java files in current directory
java_files = [f for f in os.listdir('.') if f.endswith('.java')]
# create a zip file
with zipfile.ZipFile('b2220356127.zip', 'w') as zipf:
    for f in java_files:
        zipf.write(f)
