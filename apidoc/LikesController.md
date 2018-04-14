# Likes (좋아요 or 공감)
***

## Likes 등록 및 해제
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>GET</td>
<td>board/{categoryId}/{postNo}/{commentId}</td>
</tr>
</table>

### 응답 바디 (ex) board/1/1/6
<pre><code>
{
    "statusEnum": "SUCCESS",
    "data": {
        "id": 6,
        "categoryId": 1,
        "postNo": 1,
        "userId": 5,
        "commentBody": "1/1 댓글1",
        "likesCount": 2
    },
    "msg": "좋아요"
}
</code></pre>