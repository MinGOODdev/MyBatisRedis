# Comment

***

## 댓글 등록
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>POST</td>
<td>board/{categoryId}/{postNo}/comment</td>
</tr>
</table>

### 요청 헤더
<pre><code>{
   Content-Type: application/json
}</code></pre>

### 요청 바디 (ex) board/1/1
<pre><code>{
	"commentBody" : "1/2 댓글2"
}
</code></pre>

### 응답 바디
<pre><code>{
    "statusEnum": "SUCCESS",
    "data": {
        "id": 20,
        "categoryId": 2,
        "userId": 1,
        "postNo": 2,
        "commentBody": "2/2 댓글2",
        "likesCount": 0
    },
    "msg": "댓글 등록 성공"
}
</code></pre>

***

## 댓글 삭제 (Likes 포함)
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>DELETE</td>
<td>board/{categoryId}/{postNo}/{commentId}</td>
</tr>
</table>