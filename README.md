# ESL Play Framework Template Minimal v2

Encentral Solutions Play Framework Minimal Project Template

## Necessary Files to be edited

### entities Module

#### - /src/main/resources/META-INF/persistence.xml
```xml
<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/{YOUR_DB_NAME}"/>
<property name="javax.persistence.jdbc.user" value="{YOUR_DB_USERNAME}"/>
<property name="javax.persistence.jdbc.password" value="{YOUR_DB_PASSWORD}"/>
```

### service-gateway Module

#### - /conf/META-INF/persistence.xml
```xml
<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/{YOUR_DB_NAME}"/>
<property name="javax.persistence.jdbc.user" value="{YOUR_DB_USERNAME}"/>
<property name="javax.persistence.jdbc.password" value="{YOUR_DB_PASSWORD}"/>
```

#### - /conf/application.conf
```
db.default.url="jdbc:postgresql://localhost:5432/{YOUR_DB_NAME}"

db.default.username={YOUR_DB_USERNAME}
db.default.password="{YOUR_DB_PASSWORD}"
```

##### Finally, change README.md :)
