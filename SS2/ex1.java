/*Kiểm tra xem một User có phải là Admin hay không (trả về true/false)→ Predicate<User>
Lý do:
Predicate dùng để kiểm tra một điều kiện và trả về boolean (true/false).
Ở đây nhiệm vụ là kiểm tra role của User có phải "Admin" hay không.
Chuyển đổi một đối tượng User thành một chuỗi String chứa thông tin username
→ Function<User, String>

Lý do:
Function<T, R> dùng khi cần biến đổi một đối tượng thành một giá trị khác.
Ở đây là từ User → String.

In thông tin chi tiết của User ra màn hình Console
→ Consumer<User>

Lý do:
Consumer dùng khi nhận một tham số và thực hiện hành động, không trả về giá trị.
Việc in ra màn hình chỉ là hành động xử lý.
Khởi tạo một đối tượng User mới với các giá trị mặc định
→ Supplier<User>

Lý do:
Supplier dùng khi không nhận tham số nhưng trả về một đối tượng.
Ở đây dùng để tạo User mới.
*/


