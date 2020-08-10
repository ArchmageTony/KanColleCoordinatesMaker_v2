# 舰娘立绘坐标文件生成器 v2  
v1版本为`a5566123s`所制作：[NGA帖子地址](https://bbs.nga.cn/read.php?tid=13252143&rand=442) [GitHub项目地址](https://github.com/a5566123s/KanColleCoordinatesMaker)  
本程序功能与v1版本相同，均为通过解析**官方api_start2数据**生成用户指定的立绘文件坐标数据文件（.config.ini）。主要作用为保存节日限定立绘的相应的坐标，方便之后通过魔改可在非限定时期继续使用限定立绘（本程序仅能保存坐标不能保存立绘）。由于游戏二期对数据格式的修改导致v1版本的程序无法正常工作，故重新制作了这个程序。  
![image](https://www.crowsong.xyz/wp-content/uploads/2020/04/2020080711272327.png )  
***
## 一、运行环境  
本软件需要java11以上版本才可以运行，请前往[Oracle官网](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html "点击此处跳转") 下载。  
若你对安装java环境不是很了解可以从下方下载由我精简过的运行环境（15MB左右）。**精简过的运行环境不包含程序本身且仅可运行本程序，需要将下载下来的jar程序放入到解压后的运行环境`KCCM Runtime`文件夹中，并使用`start.bat`来启动。**  
## 二、下载
GitHub项目地址：[https://github.com/ArchmageTony/KanColleCoordinatesMaker_v2](https://github.com/ArchmageTony/KanColleCoordinatesMaker_v2)  
**解压密码：crowsong.xyz**  
你可以直接从[release](https://github.com/ArchmageTony/KanColleCoordinatesMaker_v2/releases )页面下载程序`KanColleCoordinatesMaker_v2.jar`与运行环境`KCCM Runtime.rar`。  
诚通网盘下载方式 **（解压密码：crowsong.xyz）**：  
- 程序：[KanColleCoordinatesMaker_v2.jar（诚通网盘）](https://t00y.com/file/2276132-456385405 "解压密码：crowsong.xyz")  
- 运行环境：[KCCM Runtime.rar（诚通网盘）](https://t00y.com/file/2276132-456384453 "解压密码：crowsong.xyz")  

## 三、解析数据(api_start2)准备
本程序可以解析从getData返回`api_start2`数据的`UTF-8`编码方式的TXT或者JSON文件，也可以解析Tibowl大佬解析整理过的`start2.json`和`api_mst_shipgraph.json`文件（[项目地址](https://github.com/Tibowl/api_start2  )）。  
下面将会讲述如何获取到`api_start2`数据文件  
- 1、自行下载getData数据：以chrome为例，按`F12`打开开发者工具，选择`Network`标签，打开舰娘游戏并点击`GAME START`进入游戏，进入到主港后，在开发者工具里面搜索`getData`，单击下面的`getData`，在右侧选择`Response`标签卡，将里面的内容全部复制下来，保存成`UTF-8`格式的TXT或JSON即可。  
![image](https://www.crowsong.xyz/wp-content/uploads/2020/04/2020080709371744.png )  
- 2、从Tibowl大佬那里下载整理过的`start2.json`文件：先访问[https://github.com/Tibowl/api_start2](https://github.com/Tibowl/api_start2  )，单击`start2.json`之后在右上角找到`Download`即可，若你点击后没有进入下载而是仅仅进入到了文本当中，直接右键另存为即可。  
![image](https://www.crowsong.xyz/wp-content/uploads/2020/04/2020080709414383.png )  
- 3、`api_mst_shipgraph.json`文件同理，在`parsed`文件夹中找到后选择右上角`Raw`按钮，进入到文本后直接右键另存为即可。  
![image](https://www.crowsong.xyz/wp-content/uploads/2020/04/2020080709414468.png )  

## 四、程序使用
![image](https://www.crowsong.xyz/wp-content/uploads/2020/04/2020080709530029.png )  
双击`jar`启用程序或者使用`start.bat`启动后选择`api_start2`数据文件所在位置，输入你需要提取的立绘的文件名，多个以英文逗号隔开，点击生成即可。你可以在输出文件设置当中，设置只输出哪些键值，设置完后记得点保存按钮！未勾中的内容只会输出键值，但不会输出数值，岛风go与acgpower不会读取没有数值的内容。输出出来的文件在程序所在目录的output文件夹中。  
关于获取立绘的文件名，可以通过岛风go或者acgpower等工具进行查看，图中以使用acgpower搜索朝潮为例，红色圈住的字符ID记录下来后写入到程序的输入框中即可。**请注意每个形态各自有不同的立绘文件名称！请注意有些立绘的文件名称修改过！比如万圣节的朝潮(schftfqkstha)！如果提示你未搜索到，请尝试打开数据文件手动搜索确认一下。**  
![image](https://www.crowsong.xyz/wp-content/uploads/2020/04/20200807095259100.png )  
`.config.ini`文件的使用请自行参考各自代理软件的存放方式。  
## 五、其它说明与问题
- 1、岛风go与acgpower目前均不支持`api_pa`和`api_pab`特殊攻击立绘的坐标修改，但是为了防止以后增加这个功能我就先都给写上了。  
- 2、解析采用的是gson框架，一般来说除非`api_start2`的数据结构上有非常大的修改，否则不会影响到提取的，增加的键值不会被读取，减少的键值会被读取为空后输出。  
- 3、如果手动保存`getData`的数据记得最好选用`UTF-8`的编码方式保存，格式TXT或者JSON。  
- 4、有些角色的限定立绘的名字改过，所以请确认你输入的没有问题。  
- 5、查看历史`api_start2`数据可以在Tibowl大佬项目的commit里面查看到历史记录。  
- 6、字体使用的是`SimHei`应该都有吧。。没有的自己去下载个安装一下。。。  