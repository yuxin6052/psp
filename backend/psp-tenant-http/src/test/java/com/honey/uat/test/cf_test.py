#!/usr/bin/env python
# coding: utf8
# -*-  coding:UTF-8   -*-
import unittest
import httplib
import json

import sys
import getopt
import random
import time
import urllib2
import logging
import hashlib
#115.159.114.116
schema = 'http'
home_host = '127.0.0.1'
port = '8181'
bussiness_port = '8181'
prefix = ''

# logger = logging.getLogger("loggingmodule.NomalLogger")  
# handler = logging.FileHandler("test.log")  
# formatter = logging.Formatter("[%(levelname)s][%(funcName)s][%(asctime)s]%(message)s")  
# handler.setFormatter(formatter)  
# logger.addHandler(handler)  
# logger.setLevel(logging.DEBUG)  

class mytests(unittest.TestCase):

    def setUp(self):
        if schema == 'http':
            self.conn1 = httplib.HTTPConnection(
                '%s:%s' % (home_host,port), timeout=300)
        else:
            self.conn1 = httplib.HTTPSConnection(
                '%s:%s' % (home_host,port), timeout=300)

        if schema == 'http':
            self.conn = httplib.HTTPConnection(
                '%s:%s' % (home_host,bussiness_port), timeout=300)
        else:
            self.conn = httplib.HTTPSConnection(
                '%s:%s' % (home_host,bussiness_port), timeout=300)

        obj = dict()
        obj["username"] = "admin"
  
        obj["password"] = "123456" 
        obj1 = dict()
        obj1['Content-type'] = "application/json"
        obj1['Accept'] = "application/json"
        
        self.conn1.request('POST','%s/tenant/v1/user/login'% prefix ,json.dumps(obj),obj1)
        res = self.conn1.getresponse()
        instance = json.load(res)

        headers = res.getheaders()
        
        for header in headers:
            if 'authorization' in header:
                obj1["authorization"] = header[1]
                break
        self.header = obj1
        
    def testshipment(self):

        self.conn.request('GET', '%s/tenant/v1/companyLabel/list'% prefix,
                          json.dumps({}), self.header)
        res1 = self.conn.getresponse()
        instance1 = json.load(res1)

        self.conn.request('GET', '%s/tenant/v1/company/list?queryType=1'% prefix,
                          json.dumps({}), self.header)
        res1 = self.conn.getresponse()
        instance1 = json.load(res1)


        self.conn.request('GET', '%s/tenant/compan/v1/getProvinceData?countryCode='% prefix,
                          json.dumps({}), self.header)
        res1 = self.conn.getresponse()
        instance1 = json.load(res1)

        self.conn.request('GET', '%s/tenant/device/v1/list?groupId=-1&searchString=1'% prefix,
                          json.dumps({}), self.header)
        res1 = self.conn.getresponse()
        instance1 = json.load(res1)
#         
#         self.conn.request('GET', '%s/tenant/device/v1/detail/13'% prefix,
#                           json.dumps({}), self.header)
#         res1 = self.conn.getresponse()
#         instance1 = json.load(res1)
#         
#         self.conn.request('GET', '%s/tenant/deviceGroup/v1/list'% prefix,
#                           json.dumps({}), self.header)
#         res1 = self.conn.getresponse()
#         instance1 = json.load(res1)
        
        
        self.conn.request('GET', '%s/tenant/device/v1/getLocation/13'% prefix,
                          json.dumps({}), self.header)
        res1 = self.conn.getresponse()
        instance1 = json.load(res1)
        
        obj = {"msgId":"11",'msgFlag':"request","msgType":"command","msgData":[{'deviceId':14,'action':'wipeDevice','sndTime':1451577600000,'args':{}},{'deviceId':13,'action':'wipeDevice','sndTime':1451577600000,'args':{}}]}
 
        self.conn.request('POST', '%s/tenant/device/v1/command'% prefix,
                          json.dumps(obj), self.header)
        res1 = self.conn.getresponse()
        instance1 = json.load(res1)
        
        a=1


def suite():
    suite = unittest.TestSuite()
    suite.addTest(mytests("testshipment"))
    unittest.TextTestRunner().run(suite)

if __name__ == '__main__':
    opts, args = getopt.getopt(sys.argv[1:], "")
    if len(args) >= 1:
        home_host_host = args[0]

    for i in range(len(args)):
        sys.argv.pop()
    suite()
