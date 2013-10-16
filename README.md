Seeker
======

    "Get the Snitch or die trying."
    —Oliver Wood to team Seeker, Harry Potter

## 背景

Google Reader被“不作恶”的Google关闭了。

## 介绍

Seeker的目标是做一个简单易用的阅读应用。

![](https://github.com/shelltea/seeker/diff_blob/f1e935223923fb81142b34987362b6d2eb4b28f0/src/main/webapp/WEB-INF/resources/images/screenshot-1.png?raw=true)

![](https://github.com/shelltea/seeker/diff_blob/31026f8394f692aba1caaef64949b1446134d7ef/src/main/webapp/WEB-INF/resources/images/screenshot-2.png?raw=true)

## 特性

虽然特性目前少得都没法写，但如果你也是名Java攻城师，Seeker的特性将由你决定。

## 如何运行

    git clone https://github.com/shelltea/seeker.git
    mvn jetty:run

数据库默认使用的是内置的H2，会在启动时自动创建数据库和表结构，并执行初始化。
启动后，访问：http://localhost:8080，默认用户名和密码为shelltea。
