# -*- coding: utf-8 -*-
"""
Created on Fri Jun 21 15:16:24 2013

@author: tmlewis
"""

import random
import json

mu = 0
sigma =0
runs = 3
sampleSize = 1000

outputFile = "../resources/dummyData/output"


def generateXsamples(option, x):
    sampleList = []
    
    for i in range(0,x):
        sampleList.append(random.normalvariate(mu,sigma))
                                
    writeOut(option, sampleList)
                
def writeOut(option, sampleList):
    data = {option : sampleList}
    json.dump(data,open(outputFile,'a+'))
    fo = open(outputFile,'a+')
    fo.write('\n')
    fo.close
    
if __name__=="__main__":
    mu = 5
    sigma = 3
    generateXsamples("option 1", sampleSize)
    
    mu = 7
    sigma = 5
    generateXsamples("option 2",sampleSize)            
    
                
    mu = 3
    sigma = 3
    generateXsamples("option 3", sampleSize)
                