# springcldou-配置中心
  ## 搭建基础配置中心
  * @EnableConfigServer 开启配置中心
  * yml配置拉取 github上的配置信息
  ```
    spring:
      application:
        name: cloud-config-center
      cloud:
        config:
          server:
            git:
              uri: https://github.com/myJavaHome-cca/springcloud-config.git # github地址
              force-pull: true
              username: 827430150@qq.com  # 账号信息
              password: an060150
              search-paths:
                - springcloud-config  # 指定寻找的路径     这两个只会用一个
                - /** # 查找当前仓库下的所有文件（包括文件夹下的）
          label: master   # 指定 github 分支
    说明：从github的指定路径下寻找文件
    注意:
        访问路径： http://localhost:3344/master/application-prod.yml
                     URL/{分支}/{文件名} （如果是文件夹下会自动寻找）
  ```
