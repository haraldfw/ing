from datetime import datetime

buy = -1
sell = -1
bestDiff = -1

timeBefore = datetime.now().microsecond

stockDiff = [-1, 3, -9, 2, 2, -1, 2, -1, -5]
for i in range(len(stockDiff) - 1):
    diffThisFar = 0
    for j in range(i + 1, len(stockDiff)):
        diffThisFar += stockDiff[j]
        diff = diffThisFar - stockDiff[i]
        if diff > bestDiff:
            bestDiff = diff
            buy = i
            sell = j

timeAfter = datetime.now().microsecond
print(timeBefore)
print(timeAfter)
print("%.10f" % (timeAfter - timeBefore))
