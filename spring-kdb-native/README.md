#### Sample application to show how to access kdb natively in Spring via jdbc
Code for [my post](http://subhabratachoudhury.com/p/2017/02/run-native-kdb-queries-in-spring-jdbc/)
##### Steps to run

1. First let’s start the kdb process to which the java client will connect. I am using port 7000. You can choose any port you like.
  
  ```
  ~/Workspace/sping-kdb-native/q $ rlwrap q server.q -p 7000
  KDB+ 3.4 2016.12.08 Copyright (C) 1993-2016 Kx Systems
  m32/ 4()core 8192MB subha tuchanka.local 192.168.0.101 NONEXPIRE
      
  q)quote
  time sym bid ask
  ----------------
  q)
  ```

2. Open the project in IntelliJ IDEA/Eclipse. Add [this jar](http://kx.com/q/c/jdbc.jar) to your build path and run the client.

3. Now if you query quote you should see new data coming in every second
  
  ```
  ~/Workspace/sping-kdb-native/q $ rlwrap q server.q -p 7000
  KDB+ 3.4 2016.12.08 Copyright (C) 1993-2016 Kx Systems
  m32/ 4()core 8192MB subha tuchanka.local 192.168.0.101 NONEXPIRE
      
  q)quote
  time         sym   bid    ask
  --------------------------------
  20:19:51.463 CERN  52.93  53.93
  20:19:51.463 VMW   87.76  90.76
  20:19:51.463 GOOGL 829.88 829.88
  20:19:51.463 AAPL  132.04 132.04
  20:19:51.463 YHOO  44.07  45.07
  20:19:51.463 ADI   77.48  78.48
  20:19:51.463 BIDU  181.5  181.5
  20:19:51.463 INFO  38.71  40.71
  20:19:51.463 ITW   127.12 128.12
  20:19:51.463 ADP   95.58  96.58
  20:19:51.463 STM   13.94  14.94
  20:19:51.463 CHKP  99.56  100.56
  20:19:51.463 PNR   57.96  58.96
  20:19:51.463 STX   44.8   46.8
  20:19:51.463 WDC   76.08  78.08
  20:19:51.463 XLNX  58.23  59.23
  20:19:51.463 AMD   13.56  14.56
  20:19:51.463 CTSH  56.45  57.45
  20:19:51.463 DVMT  64.84  65.84
  20:19:51.463 INFY  13.11  15.11
  ..
  q)count quote
  192225
  q)
  
  ```

#### License

Code released under the MIT license
