function postOfSendComment() {
    var questionId = $("#question_id").val();   //得到parentId
    var commentContent = $("#comment_content").val();   //得到回复内容
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data: JSON.stringify({
            "parentId":questionId,
            "content":commentContent,
            "typeId": 1
        }),
        success:function (response) {
            console.log(response);
        },
        /*要求的响应类型为json*/
        dataType:"json"
    });
    console.log(questionId + "----" + commentContent);
}