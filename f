<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>ru.spbu.apcyb</groupId>
  <artifactId>untitled</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
    <sonar.organization>EvgenDas</sonar.organization>
    <sonar.host.url>https://sonarcloud.io</sonar.host.url>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.9.0</version>
      <scope>test</scope>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.jacoco/org.jacoco.core -->
    <dependency>
      <groupId>org.jacoco</groupId>
      <artifactId>org.jacoco.core</artifactId>
      <version>0.8.8</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.jacoco/org.jacoco.agent -->
    <dependency>
      <groupId>org.jacoco</groupId>
      <artifactId>org.jacoco.agent</artifactId>
      <version>0.8.8</version>
      <scope>test</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.jacoco/org.jacoco.report -->
    <dependency>
      <groupId>org.jacoco</groupId>
      <artifactId>org.jacoco.report</artifactId>
      <version>0.8.8</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-plugin -->
    <dependency>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.0.0-M7</version>
    </dependency>

  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.0.0-M7</version>
        <configuration>
          <useSystemClassLoader>false</useSystemClassLoader>
        </configuration>
      </plugin>

      <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.8.7</version>
        <executions>
          <execution>
            <goals>
              <goal>prepare-agent</goal>
            </goals>
          </execution>
          <execution>
            <id>report</id>
            <phase>prepare-package</phase>
            <goals>
              <goal>report</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>16</source>
          <target>16</target>
        </configuration>
      </plugin>

    </plugins>
  </build>
</project>