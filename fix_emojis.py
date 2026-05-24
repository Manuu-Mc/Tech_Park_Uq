import glob, re

# Emoji UTF-8 bytes misread as Windows-1252 -> Java Unicode escape
# 👤 U+1F464: UTF-8 F0 9F 91 A4
#   F0 -> U+00F0, 9F -> U+0178 (win1252), 91 -> U+2018 (win1252), A4 -> U+00A4
# 📷 U+1F4F7: UTF-8 F0 9F 93 B7
#   F0 -> U+00F0, 9F -> U+0178, 93 -> U+201C (win1252), B7 -> U+00B7

emoji_fixes = {
    '\u00f0\u0178\u2018\u00a4': '\\uD83D\\uDC64',  # 👤
    '\u00f0\u0178\u201c\u00b7': '\\uD83D\\uDCF7',  # 📷
    '\u00f0\u0178\u008e\u00a1': '\\uD83C\\uDFA1',  # 🎡
    '\u00f0\u0178\u008e\u00a2': '\\uD83C\\uDFA2',  # 🎢
    '\u00f0\u0178\u008e\u00a0': '\\uD83C\\uDFA0',  # 🎠
    '\u00f0\u0178\u008f\u0086': '\\uD83C\\uDFC6',  # 🏆
    '\u00f0\u0178\u2019\u00b0': '\\uD83D\\uDCB0',  # 💰
    '\u00f0\u0178\u2019\u00b3': '\\uD83D\\uDCB3',  # 💳
    '\u00f0\u0178\u201d\u0094': '\\uD83D\\uDD14',  # 🔔
    '\u00f0\u0178\u2014\u00ba': '\\uD83D\\uDDFA',  # 🗺
    '\u00f0\u0178\u008c\u009f': '\\uD83C\\uDF1F',  # 🌟
    '\u00f0\u0178\u201d\u008a': '\\uD83D\\uDCCA',  # 📊
    '\u00f0\u0178\u008e\u009f': '\\uD83C\\uDF9F',  # 🎟
    '\u00f0\u0178\u008e\u00ab': '\\uD83C\\uDFAB',  # 🎫
    '\u00f0\u0178\u008e\u00aa': '\\uD83C\\uDFAA',  # 🎪
    '\u00f0\u0178\u201d\u0092': '\\uD83D\\uDD12',  # 🔒
    '\u00f0\u0178\u2019\u00a8': '\\uD83D\\uDC68',  # 👨
    '\u00f0\u0178\u2019\u00a9': '\\uD83D\\uDC69',  # 👩
    '\u00f0\u0178\u2019\u00b6': '\\uD83D\\uDC76',  # 👶
    '\u00f0\u0178\u008c\u008a': '\\uD83C\\uDF0A',  # 🌊
    '\u00f0\u0178\u008c\u009e': '\\uD83C\\uDF1E',  # 🌞
    '\u00f0\u0178\u2018\u00a8': '\\uD83D\\uDC68',  # 👨 alt
    '\u00f0\u0178\u2018\u00a9': '\\uD83D\\uDC69',  # 👩 alt
}

all_files = glob.glob('src/main/java/**/*.java', recursive=True)
total = 0
for fname in all_files:
    with open(fname, encoding='utf-8') as f:
        text = f.read()
    original = text
    for bad, good in emoji_fixes.items():
        text = text.replace(bad, good)
    if text != original:
        with open(fname, 'w', encoding='utf-8') as f:
            f.write(text)
        print('Fixed:', fname.split('/')[-1].split('\\')[-1])
        total += 1
print('Total:', total)
