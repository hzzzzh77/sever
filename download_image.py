import requests
import os
from bs4 import BeautifulSoup
from urllib.parse import urljoin, unquote

# 设置保存目录
save_dir = r"E:\zp"
if not os.path.exists(save_dir):
    os.makedirs(save_dir)

# 获取无水印的URL
def get_watermark_free_url(url):
    # 尝试修改URL以去除水印
    if 'gd-hbimg' in url:
        return url.replace('gd-hbimg', 'hbimg')  # 替换域名
    return url

# 获取图片URL
def get_image_url(page_url):
    # 解码URL
    decoded_url = unquote(page_url)
    print(f"解码后的URL: {decoded_url}")

    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36'
    }
    response = requests.get(decoded_url, headers=headers)
    if response.status_code == 200:
        soup = BeautifulSoup(response.text, 'html.parser')
        # 查找图片元素
        img_tag = soup.find('img', {'src': True})
        if img_tag:
            img_url = img_tag['src']
            return get_watermark_free_url(img_url)
    return None

# 下载图片
def download_image(image_url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36'
    }
    try:
        img_response = requests.get(image_url, headers=headers)
        if img_response.status_code == 200:
            # 生成文件名
            file_name = os.path.basename(image_url).split('?')[0]  # 去掉URL参数
            save_path = os.path.join(save_dir, file_name)
            
            # 保存图片
            with open(save_path, 'wb') as f:
                f.write(img_response.content)
            print(f"图片已保存为: {save_path}")
        else:
            print(f"下载图片失败，状态码: {img_response.status_code} (URL: {image_url})")
    except Exception as e:
        print(f"下载图片时出错: {e}")

# 主函数
def main():
    page_url = input("请输入花瓣网的页面URL: ")
    if not page_url:
        print("URL 不能为空！")
        return

    # 获取图片URL
    image_url = get_image_url(page_url)
    if image_url:
        print(f"获取到的图片URL: {image_url}")
        # 下载图片
        download_image(image_url)
    else:
        print("未找到图片URL，请检查网页内容。")

if __name__ == "__main__":
    main()