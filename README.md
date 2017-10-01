# mybatis_Test
mybatis操作数据库详解

1、原始dao开发，需要些接口和实现类，独立写配置文件
2、mapper代理开发，只需写接口，接口名和mapper.xml文件名需一致且在同一目录下，接口需按照mapper.xml中的规范去编写
3、配置文件需在总配置文件SqlMapConfig.xml加载（单个加载、通过mapper接口加载多个配置文件（只适用mapper代理的方法 ））
4、总配置文件SqlMapConfig.xml可以设置别名（单个定义别名、批量定义别名）
3、test源文件下放的下是测试程序，使用junit test

