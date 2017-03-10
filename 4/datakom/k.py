strings = []
for year in range(2000, 2017):
    for quarter in range(1, 5):
        strings.append(str(year) + 'K0' + str(quarter))

print(','.join(strings))
