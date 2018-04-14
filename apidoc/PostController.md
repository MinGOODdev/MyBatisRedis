# Post
***
## All Post List
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>GET</td>
<td>/board/all</td>
</tr>
</table>

### 응답 바디
<pre><code>{
    "statusEnum": "SUCCESS",
    "data": [
        {
            "id": 18,
            "categoryId": 1,
            "categoryName": "공지사항",
            "userId": 1,
            "no": 1,
            "title": "1번 카테고리",
            "body": "1번 게시글",
            "hit": 30,
            "comments": null
        },
        {
            "id": 19,
            "categoryId": 1,
            "categoryName": "공지사항",
            "userId": 1,
            "no": 2,
            "title": "1번 카테고리",
            "body": "2번 게시글",
            "hit": 10,
            "comments": null
        },
        {
            "id": 20,
            "categoryId": 1,
            "categoryName": "공지사항",
            "userId": 1,
            "no": 3,
            "title": "1번 카테고리",
            "body": "3번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 22,
            "categoryId": 1,
            "categoryName": "공지사항",
            "userId": 1,
            "no": 4,
            "title": "1번 카테고리",
            "body": "4번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 23,
            "categoryId": 1,
            "categoryName": "공지사항",
            "userId": 1,
            "no": 5,
            "title": "1번 카테고리",
            "body": "5번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 24,
            "categoryId": 2,
            "categoryName": "자유게시판",
            "userId": 1,
            "no": 1,
            "title": "2번 카테고리",
            "body": "1번 게시글",
            "hit": 3,
            "comments": null
        },
        {
            "id": 25,
            "categoryId": 2,
            "categoryName": "자유게시판",
            "userId": 2,
            "no": 2,
            "title": "2번 카테고리",
            "body": "2번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 26,
            "categoryId": 2,
            "categoryName": "자유게시판",
            "userId": 2,
            "no": 3,
            "title": "2번 카테고리",
            "body": "3번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 27,
            "categoryId": 2,
            "categoryName": "자유게시판",
            "userId": 2,
            "no": 4,
            "title": "2번 카테고리",
            "body": "4번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 28,
            "categoryId": 3,
            "categoryName": "문의사항",
            "userId": 3,
            "no": 1,
            "title": "3번 카테고리",
            "body": "1번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 29,
            "categoryId": 3,
            "categoryName": "문의사항",
            "userId": 3,
            "no": 2,
            "title": "3번 카테고리",
            "body": "2번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 30,
            "categoryId": 3,
            "categoryName": "문의사항",
            "userId": 4,
            "no": 3,
            "title": "3번 카테고리",
            "body": "3번 게시글",
            "hit": 2,
            "comments": null
        },
        {
            "id": 31,
            "categoryId": 3,
            "categoryName": "문의사항",
            "userId": 4,
            "no": 4,
            "title": "3번 카테고리",
            "body": "4번 게시글",
            "hit": 1,
            "comments": null
        }
    ],
    "msg": "전체 게시글 목록"
}
</code></pre>

***

## 카테고리별 Post List
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>GET</td>
<td>board/{categoryId}</td>
</tr>
</table>

### 응답 바디 (ex) board/1
<pre><code>{
    "statusEnum": "SUCCESS",
    "data": [
        {
            "id": 23,
            "categoryId": 1,
            "categoryName": "공지사항",
            "userId": 1,
            "no": 5,
            "title": "1번 카테고리",
            "body": "5번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 22,
            "categoryId": 1,
            "categoryName": "공지사항",
            "userId": 1,
            "no": 4,
            "title": "1번 카테고리",
            "body": "4번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 20,
            "categoryId": 1,
            "categoryName": "공지사항",
            "userId": 1,
            "no": 3,
            "title": "1번 카테고리",
            "body": "3번 게시글",
            "hit": 0,
            "comments": null
        },
        {
            "id": 19,
            "categoryId": 1,
            "categoryName": "공지사항",
            "userId": 1,
            "no": 2,
            "title": "1번 카테고리",
            "body": "2번 게시글",
            "hit": 10,
            "comments": null
        },
        {
            "id": 18,
            "categoryId": 1,
            "categoryName": "공지사항",
            "userId": 1,
            "no": 1,
            "title": "1번 카테고리",
            "body": "1번 게시글",
            "hit": 30,
            "comments": null
        }
    ],
    "msg": "해당 카테고리의 게시글 리스트"
}
</code></pre>

***

## 게시글 등록
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>POST</td>
<td>{categoryId}/post</td>
</tr>
</table>

### 요청 헤더
<pre><code>{
   Content-Type: application/json
}</code></pre>

### 요청 바디
<pre><code>{
	"title" : "3번 카테고리",
	"body" : "4번 게시글"
}
</code></pre>

### 응답 바디
<pre><code>{
    "statusEnum": "SUCCESS",
    "data": {
        "id": 31,
        "categoryId": 3,
        "userId": 4,
        "no": 4,
        "title": "3번 카테고리",
        "body": "4번 게시글"
    },
    "msg": "게시글 등록"
}
</code></pre>

***

## 게시글 삭제 (Comment & Likes 포함)
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>DELETE</td>
<td>{categoryId}/{no}</td>
</tr>
</table>