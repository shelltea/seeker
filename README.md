Seeker
======

    "Get the Snitch or die trying."
    —Oliver Wood to team Seeker, Harry Potter

## 背景

Google Reader被“不作恶”的Google关闭了。

## 介绍

Seeker的目标是做一个简单易用的阅读应用。

![](https://raw.githubusercontent.com/shelltea/seeker/develop/src/main/webapp/WEB-INF/resources/images/screenshot-1.png)

![](https://raw.githubusercontent.com/shelltea/seeker/develop/src/main/webapp/WEB-INF/resources/images/screenshot-2.png)

## 特性

虽然特性目前少得都没法写，但如果你也是名Java攻城师，Seeker的特性将由你决定。

## 如何运行

    git clone https://github.com/shelltea/seeker.git
    mvn jetty:run

数据库默认使用的是内置的H2，会在启动时自动创建数据库和表结构，并执行初始化。
启动后，访问：http://localhost:8080，默认用户名和密码为shelltea。
