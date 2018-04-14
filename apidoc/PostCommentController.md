# 카테고리별 해당 게시글 조회 (댓글 포함)
***

## 게시글 조회 (댓글 최신순)
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>GET</td>
<td>/board/{categoryId}/{postNo}</td>
</tr>
</table>

### 응답 바디 (ex) board/1/2
<pre><code>{
    "statusEnum": "SUCCESS",
    "data": {
        "id": 19,
        "categoryId": 1,
        "categoryName": "공지사항",
        "userId": 1,
        "no": 2,
        "title": "1번 카테고리",
        "body": "2번 게시글",
        "hit": 8,
        "comments": [
            {
                "id": 19,
                "categoryId": 1,
                "postNo": 2,
                "userId": 9,
                "commentBody": "1/2 댓글2",
                "likesCount": 2
            },
            {
                "id": 18,
                "categoryId": 1,
                "postNo": 2,
                "userId": 9,
                "commentBody": "1/2 댓글1",
                "likesCount": 0
            },
            {
                "id": 17,
                "categoryId": 1,
                "postNo": 2,
                "userId": 9,
                "commentBody": "1/2 댓글3",
                "likesCount": 1
            },
            {
                "id": 9,
                "categoryId": 1,
                "postNo": 2,
                "userId": 5,
                "commentBody": "1/2 댓글2",
                "likesCount": 0
            },
            {
                "id": 8,
                "categoryId": 1,
                "postNo": 2,
                "userId": 5,
                "commentBody": "1/2 댓글1",
                "likesCount": 2
            }
        ]
    },
    "msg": "해당 카테고리의 선택된 게시글 댓글 포함 (댓글 최신순)"
}
</code></pre>

## 게시글 조회 (좋아요 개수순)
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>GET</td>
<td>/board/{categoryId}/{postNo}/likes</td>
</tr>
</table>

### 응답 바디 (ex) board/1/2/likes
<pre><code>{
    "statusEnum": "SUCCESS",
    "data": {
        "id": 19,
        "categoryId": 1,
        "categoryName": "공지사항",
        "userId": 1,
        "no": 2,
        "title": "1번 카테고리",
        "body": "2번 게시글",
        "hit": 9,
        "comments": [
            {
                "id": 8,
                "categoryId": 1,
                "postNo": 2,
                "userId": 5,
                "commentBody": "1/2 댓글1",
                "likesCount": 2
            },
            {
                "id": 19,
                "categoryId": 1,
                "postNo": 2,
                "userId": 9,
                "commentBody": "1/2 댓글2",
                "likesCount": 2
            },
            {
                "id": 17,
                "categoryId": 1,
                "postNo": 2,
                "userId": 9,
                "commentBody": "1/2 댓글3",
                "likesCount": 1
            },
            {
                "id": 9,
                "categoryId": 1,
                "postNo": 2,
                "userId": 5,
                "commentBody": "1/2 댓글2",
                "likesCount": 0
            },
            {
                "id": 18,
                "categoryId": 1,
                "postNo": 2,
                "userId": 9,
                "commentBody": "1/2 댓글1",
                "likesCount": 0
            }
        ]
    },
    "msg": "해당 카테고리의 선택된 게시글 댓글 포함 (좋아요 갯수 순)"
}
</code></pre>