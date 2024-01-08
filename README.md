해당 프로젝트에서 QueryDSL 로 구현하려고 하는 JOIN 쿼리

select
st.student_id,
st.name,
st.age,
sc.subject,
sc.score
[st 테이블에서 student_id , name ,age 를 검색하고 sc 테이블에서 subject , score 를 검색]

from student st
[기준이 되는 테이블 st]

join score sc on st.student_id = sc.student_id
[sc 를 join 하고, 두 테이블에서 id 가 같은것 끼리 필터링 함.(on)]

where st.student_id = 1
[필터링한 결과에서 student_id 가 1 인 것을 검색 (다른 아이디 값을 넣어도 됌)]

order by student_id;
[정렬. student_id를 기준으로 asc , desc 를 선택할수있음]
