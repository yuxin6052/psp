#!/usr/bin/env python
# coding: utf8
# -*-  coding:UTF-8   -*-
import unittest
import httplib
import json
from pypinyin import pinyin, lazy_pinyin
import sys
import getopt
import random
import time
import urllib2
import logging
import hashlib
import requests
 

schema = 'http'
wholesale_host = '172.81.242.29'
pic_path = 'C:\\Users\\h197980\\Pictures\\wechat.jpg'
port = '8888'
prefix = ''
 

logger = logging.getLogger("loggingmodule.NomalLogger")  
handler = logging.FileHandler("test.log")  
formatter = logging.Formatter("[%(levelname)s][%(funcName)s][%(asctime)s]%(message)s")  
handler.setFormatter(formatter)  
logger.addHandler(handler)  
logger.setLevel(logging.DEBUG)  


class mytests(unittest.TestCase):

    def setUp(self):
        global wholesale_host
        # 10.77.87.87
        #         acscloud.honeywell.com.cn  /00100009/v1.1  159.99.93.113
        
#         f=open('test.log','wb')
#         f.truncate()
        if schema == 'http':
            self.conn = httplib.HTTPConnection(
                '%s:%s' % (wholesale_host,port), timeout=300)
        else:
            self.conn = httplib.HTTPSConnection(
                '%s:%s' % (wholesale_host,port), timeout=300)
        
        
        obj = dict()
        obj["username"] = "honeywell"
  
        obj["password"] = "123456" 
        obj1 = dict()
        obj2 = dict()
        obj1['Content-type'] = "application/json"
        obj1['Accept'] = "application/json"
        
        self.conn.request('POST','%s/tenant/user/v1/login'% prefix ,json.dumps(obj),obj1)
        res = self.conn.getresponse()
        instance = json.load(res)

        headers = res.getheaders()
        
        for header in headers:
            if 'authorization' in header:
                obj1["authorization"] = header[1]
                obj2["authorization"] = header[1]
                break
        self.header = obj1
        
        self.header1 = obj2
   
        
    def testAppCreate(self):
        
 
        self.conn.request('GET', '%s/tenant/userAnalysis/v1/show?appKey=xxx-0000-0001&type=1&startTime=2018-10-01%%2000&endTime=2018-11-01%%2000'% prefix,
                          json.dumps({}), self.header)
        res1 = self.conn.getresponse()
        instance2 = json.load(res1)
        
        
     
        
        a=1

    
    
    
    def tearDown(self):
        self.conn.close()

   
           
   
        


def suite():
    suite = unittest.TestSuite()

    # class mytests
    

#     suite.addTest(mytests("testindex"))
#     suite.addTest(mytests("testtransaction"))
#     suite.addTest(mytests("teststatistic"))
#     suite.addTest(mytests("testcategory"))
#     suite.addTest(mytests("testproduct"))
#     suite.addTest(mytests("testshop"))
#     suite.addTest(mytests("testsupplier"))
#     suite.addTest(mytests("testcustomer"))
#     suite.addTest(mytests("testemployee"))
#     suite.addTest(mytests("testfeedback"))
    suite.addTest(mytests("testAppCreate"))
#     suite.addTest(mytests("testaccount"))
#     suite.addTest(mytests("testflow"))
#     suite.addTest(mytests("testrefund"))



    # class dealertests
#     suite.addTest(dealertests("testcompany"))
    #suite.addTest(dealertests("testdealer"))

    unittest.TextTestRunner().run(suite)

if __name__ == '__main__':
    #global wholesale_host
    opts, args = getopt.getopt(sys.argv[1:], "")
    if len(args) >= 1:
        wholesale_host = args[0]

    for i in range(len(args)):
        sys.argv.pop()

    #unittest.main()
    suite()
