# DynamicFetchSpecification

Custom base repository boilerplate that solves the error when using pageable specification with dynamic criteria query containing fetch join

## Error

org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list...

## Method

**Page**\<**T**> **findAll**(**Specification**\<**T**> spec, **Pageable** pageable)

[Interface JpaSpecificationExecutor\<T>](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaSpecificationExecutor.html)

## Explanation

I was trying to build a dynamic criteria specification which contains one-to-many fetch join relationships.

On my research to solve the issue, I noticed that the SimpleJpaRepository implementation of the above method always execute a count query first to validate query result list.

And for this particular case, the count query will be affected by the existing fetch join in the dynamic criteria query.

## Solution

I have a built a custom base repository that avoids the count query performed by SimpleJpaRepository which, in my opinion, is a better fix than the workaround proposed on the issue [DATAJPA-105](https://github.com/spring-projects/spring-data-jpa/issues/532) page


**Links**

[Custom Implementations for Spring Data Repositories](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations)

[Customize the Base Repository](https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#repositories.customize-base-repository)

[Class SimpleJpaRepository<T,ID>](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/support/SimpleJpaRepository.html)
