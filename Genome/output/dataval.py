import matplotlib.pyplot as plt
import numpy as np


def read_nodedist():
	data = []
	with open('NodeDist.txt', 'r') as f:
	    data_temp = f.read().split(",")
	    for d in data_temp:
	    	try:
	    		data.append(int(d))
	    	except Exception as e:
	    		print(e)
	return data

def read_gcdist():
	data = []
	with open('GCDist.txt', 'r') as f:
		data_temp = f.read().replace("\n","")
		for i in data_temp:
			data.append(int(i))
	return data




plt.hist(read_nodedist(), density=True, bins=100)  # `density=False` would make counts
plt.ylabel('Distribution')
plt.xlabel('NodeDegreeDistribution')
plt.show()

