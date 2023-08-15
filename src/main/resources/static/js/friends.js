let friendList = document.getElementById("friend-list");

function addFriendItem(friend) {

	for (let i = 0; i < friend.length; i++) {
		let friendItem = document.createElement("li");
		friendItem.classList.add("list-group-item", "d-flex", "justify-content-between", "align-items-center");
		friendItem.innerHTML = `
                <img id="profile_photo_preview" class="profile-photo-preview avatar" src="data:image/jpeg;base64,${friend[i].friend.upic}">
                <div class="friend-name">${friend[i].friend.uname}</div>
                <div class="friend-actions">
                    <a href="friendDetail/fuid=${friend[i].friend.uid}" class="btn btn-sm btn-info mr-2">查看個人檔案</a>
                    <button class="btn btn-sm btn-danger delete-friend" data-fid='${friend[i].friend.uid}'>刪除</button>
                </div>
            `;
		friendList.appendChild(friendItem);
	}
}
$(function() {

	fetch("getFriends?uid=1&fStatus=2").then(resp => resp.json()).then(data => {

		addFriendItem(data);
		$(".delete-friend").on("click", function(e) {
			e.stopPropagation();
			let fId = $(this).data("fid");
			fetch(`deleteFriend?fId=${fId}&uid=1`).then(resp => resp.json()).then(data => { window.location.reload(); })
		});
		
		
		// 邀請中
	//$("#invited").on("click", function() {
		//	fetch("getFriends?uid=1&fStatus=0").then(resp => resp.json()).then(data => {
		//		addFriendItem(data);
		//	});
		//});
		
		//  待確認
				$("#pending").on("click", function() {
			fetch("getFriends?uid=1&fStatus=0").then(resp => resp.json()).then(data => {
				addFriendItem(data);
			});
		});
		
		
		
		
	})
})