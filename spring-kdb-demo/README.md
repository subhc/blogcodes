#### Sample application to show how to access a kdb server in Spring via jdbc
Code for [my post](http://subhabratachoudhury.com/p/2016/03/interfacing-java-spring-with-kdb/)
##### Steps to run

1. First let’s start a kdb process to which the java client will connect. I am using port 7000. You can choose any port you like.
  
  ``````````
  $ ./q
  KDB+ 3.3 2016.03.14 Copyright (C) 1993-2016 Kx Systems
  m32/ 4()core 8192MB subha tuchanka.local 192.168.0.101 NONEXPIRE
  
  q)\p 7000
  q)
  ````````

2. Add [this jar](http://kx.com/q/c/jdbc.jar) to your build path and run the client.

##### Here's what happens

![screencast](http://subhabratachoudhury.com/img/P2_01.gif)


#### License

Code released under the MIT license
