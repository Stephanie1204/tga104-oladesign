//function img preview
function img_preview_input1() {
	const file_element = document.getElementById("p_file1");
	file_element.addEventListener("change", function(e) {
		const picture_1 = document.getElementsByClassName("picture_1")[0];
		picture_1.innerHTML = ""; // 清空
		const preview_text = document.querySelector("div#preview1");
		preview_text.style.display = "none"; //預覽文字去除
		let maxLength = this.files.length;
		if (maxLength > 3) {
			maxLength = 3;
		}
		for (let i = 0; i < maxLength; i++) {
			let reader = new FileReader(); // 用來讀取檔案
			reader.readAsDataURL(this.files[i]); // 讀取檔案
			reader.addEventListener("load", function() {
				let li_html = `<li style="display: inline-block"><img src="${reader.result}" class="preview"></li>`;
				picture_1.insertAdjacentHTML("beforeend", li_html); // 加進節點
			});
		}
	});
}

img_preview_input1();