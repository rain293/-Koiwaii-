import re
import datetime

# Đoạn mã ban đầu
code = '''
def __init__(self):
        super().__init__()

        self.now_dt = datetime.datetime.now()
        """実行日時
        """

        self.startup_command_pt = scol.BinaryPoint()
        """順次起動群指令
        """

        self.startup_command_off_time_pt = scol.AnalogPoint()
        """順次起動群指令OFF時間
        """

        self.shutdown_command_pt = scol.BinaryPoint()
        """順次停止群指令
        """

        self.shutdown_command_off_time_pt = scol.AnalogPoint()
        """順次停止群指令OFF時間
        """

        self.startup_shutdown_command_on_time_cnt = 0
        """順次起動／順次停止群指令ON時間カウンタ
        """

        self.control_command = 3
        """制御指令
        """
'''

def format_code(original_code):
    # Tách các dòng
    lines = original_code.strip().split('\n')
    formatted_lines = []

    # Tạo danh sách các biến và comment
    for line in lines:
        # Tìm kiếm biến và comment
        match = re.search(r'^(.*?)\s*=\s*(.*?)(\s*("""(.*?)""")?)?$', line)
        if match:
            variable_part = match.group(1).strip() + " = " + match.group(2).strip()
            comment = match.group(4).strip() if match.group(4) else ""
            formatted_lines.append((variable_part, comment))

    # Xác định chiều dài tối đa cho các biến
    max_length = max(len(var) for var, _ in formatted_lines)

    # Tạo dòng đã định dạng với comment thẳng hàng
    formatted_output = []
    for var, comment in formatted_lines:
        if comment:
            formatted_line = f"{var.ljust(max_length)}  # {comment}"
            formatted_output.append(formatted_line)
        else:
            formatted_output.append(var)

    return '\n'.join(formatted_output)

# Chạy hàm và in kết quả
formatted_code = format_code(code)
print(formatted_code)
