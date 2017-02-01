import csv
input_file = open('login.feature', 'rb')
output_file = open('one.csv', 'wb')
reader = csv.reader(input_file, delimiter=" ")
writer = csv.writer(output_file)
input_string_keywords = ["inputs", "input"]
f=["TestCase","Keyword", "Object", "ObjectType", "Value\r"]

for row in reader:
    if 'Scenario:' in row:
        f.append(row[row.index('Scenario:') + 1] + "\r")
    if (','in f):
            f.remove(',')
    if 'URL' in row:
        f.append(',URL,,')
        f.append(row[row.index('URL') + 2] +"\r")             
    if 'inputs' in row:
        f.append(',input_text')
        f.append(row[row.index('inputs') + 1])
        if row[row.index('inputs') + 2] == 'by':
            f.append(row[row.index('inputs') + 3])
            if row[row.index('inputs') + 4] == 'as':
                f.append(row[row.index('inputs') + 5] +'\r')
    if 'clicks' in row:
        f.append(',click')
        f.append(row[row.index('clicks') + 1])
        if row[row.index('clicks') + 2] == 'by':
            f.append(row[row.index('clicks') + 3]+',\r')
    if 'radiobutton' in row:
        f.append(',select_radiobutton')
        f.append(row[row.index('radiobutton')+1])
        if row[row.index('radiobutton')+2]=='by':
            f.append(row[row.index('radiobutton')+3])
            if row[row.index('radiobutton')+4]=='as':
                f.append(row[row.index('radiobutton')+5]+'\r')
    if 'dropdown' in row:
        f.append(',select_button')
        f.append(row[row.index('dropdown')+1])
        if row[row.index('dropdown')+2]=='by':
            f.append(row[row.index('dropdown')+3])
            if row[row.index('dropdown')+4]=='as':
                f.append(row[row.index('dropdown')+5]+'\r')
    if 'webpage' in row:
        f.append(',check,,')
        f.append(row[row.index('webpage') -1]+',\r')           
     
resultFile = open('one.csv', 'w')       
for r in f:
    if ("\r" not in r):
        resultFile.write(r + ",")
    else:
        resultFile.write(r)
resultFile.close()
   

print f
print 'success'


input_file.close()
output_file.close()

