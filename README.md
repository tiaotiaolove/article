# blog
文章类型的标准产品

## 总体技术栈
1.spring boot
2.spring mvc
3.mybatis

## 提示
application.yml中
```yaml
spring:
  profiles:
    #local/dev/develop/test 才生成swagger2 API接口,访问url为/swagger-ui.html
    active: local
```

## 代码规范
严格遵守阿里编码规约(idea插件)