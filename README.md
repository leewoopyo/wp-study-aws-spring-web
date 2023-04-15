# '스프링부트와 AWS로 혼자 구현하는 웹 서비스'

## # spring boot 버전  : 2.7.10

## # 빌드 도구  : Gradle

## # 형상관리 도구  : Git, Github
형상관리 정책은 git flow 를 따른다.

하지만 개인 스터디이니만큼 master와 develop만 두기로 한다.

개발 단위 별로, master에서 feature/{브랜치명} 으로 브랜치를 생성하고

작업 후엔 테스트를 위해 develop에 merge를 한다

테스트 후엔 master에 Pull Request를 진행한다.


    ### 1. master
    ### 2. release
    ### 3. test
    ### 4. develop

* [gitFlow 설명](https://gist.github.com/ihoneymon/a28138ee5309c73e94f9) 


## # profile
어플리케이션 설정 정보는 git flow에 따른 4개의 소스 구분 + local을 하려고 했으나,

개인 프로젝트이기때문에 local, dev, prod 3개만 두기로 한다.


    ### 1. prod
    ### 2. stg
    ### 3. test
    ### 4. dev
    ### 5. local


## # JAVA 표준 ORM (JPA) 사용

## # DB는 H2DB 사용

## # 화면 템플릿 mustache 사용
스프링 부트 2.7 버전부터는 mustache 한글 인코딩 문제가 발생하기 때문에
2.6 버전으로 수정함
