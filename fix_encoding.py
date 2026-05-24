import os, glob

all_files = glob.glob('src/main/java/**/*.java', recursive=True)

# Map of double-encoded sequences -> correct UTF-8
fixes = [
    # Standard accented chars double-encoded (Ã³ = ó, etc.)
    ('\u00c3\u00b3', '\u00f3'),  # ó
    ('\u00c3\u00a9', '\u00e9'),  # é
    ('\u00c3\u00b1', '\u00f1'),  # ñ
    ('\u00c3\u00a1', '\u00e1'),  # á
    ('\u00c3\u00ad', '\u00ed'),  # í
    ('\u00c3\u00ba', '\u00fa'),  # ú
    ('\u00c3\u00a0', '\u00e0'),  # à
    ('\u00c3\u00bc', '\u00fc'),  # ü
    ('\u00c3\u00b6', '\u00f6'),  # ö
    # Uppercase accented
    ('\u00c3\u0081', '\u00c1'),  # Á
    ('\u00c3\u0089', '\u00c9'),  # É
    ('\u00c3\u008d', '\u00cd'),  # Í
    ('\u00c3\u0093', '\u00d3'),  # Ó
    ('\u00c3\u009a', '\u00da'),  # Ú
    ('\u00c3\u0091', '\u00d1'),  # Ñ
    # Â sequences (latin1 misread of 2-byte UTF-8 starting with 0xC2)
    ('\u00c2\u00a1', '\u00a1'),  # ¡
    ('\u00c2\u00bf', '\u00bf'),  # ¿
    ('\u00c2\u00b0', '\u00b0'),  # °
    ('\u00c2\u00aa', '\u00aa'),  # ª
    ('\u00c2\u00ba', '\u00ba'),  # º
    # Ã" special - Ó appeared as Ã" in some editors (0xC3 0x93 but shown as Ã")
    ('Ã\u201c', '\u00d3'),       # Ó shown as Ã" (curly quote artifact)
    ('Ã\u00d3', '\u00d3'),       # Ó double
    # emoji: 👤 U+1F464 -> UTF-8 F0 9F 91 A4 -> latin1 misread = ð\x9f\x91\xa4
    ('\u00f0\u009f\u0091\u00a4', '\U0001f464'),  # 👤
    ('\u00f0\u009f\u0093\u00b7', '\U0001f4f7'),  # 📷
    ('\u00f0\u009f\u008e\u00a1', '\U0001f3a1'),  # 🎡
    ('\u00f0\u009f\u008e\u00a2', '\U0001f3a2'),  # 🎢
    ('\u00f0\u009f\u008e\u00a0', '\U0001f3a0'),  # 🎠
    ('\u00f0\u009f\u008f\u0086', '\U0001f3c6'),  # 🏆
    ('\u00f0\u009f\u0092\u00b0', '\U0001f4b0'),  # 💰
    ('\u00f0\u009f\u0092\u00b3', '\U0001f4b3'),  # 💳
    ('\u00f0\u009f\u0094\u0094', '\U0001f514'),  # 🔔
    ('\u00f0\u009f\u0097\u00ba', '\U0001f5fa'),  # 🗺
    ('\u00f0\u009f\u008c\u009f', '\U0001f31f'),  # 🌟
    ('\u00f0\u009f\u0093\u008a', '\U0001f4ca'),  # 📊
    ('\u00f0\u009f\u008e\u009f', '\U0001f39f'),  # 🎟
    ('\u00f0\u009f\u008e\u00ab', '\U0001f3ab'),  # 🎫
    ('\u00f0\u009f\u008e\u00aa', '\U0001f3aa'),  # 🎪
    ('\u00f0\u009f\u0094\u0092', '\U0001f512'),  # 🔒
    ('\u00f0\u009f\u0091\u00a8', '\U0001f468'),  # 👨
    ('\u00f0\u009f\u0091\u00a9', '\U0001f469'),  # 👩
    ('\u00f0\u009f\u0091\u00b6', '\U0001f476'),  # 👶
    ('\u00f0\u009f\u008c\u008a', '\U0001f30a'),  # 🌊
    ('\u00f0\u009f\u008c\u009e', '\U0001f31e'),  # 🌞
    # soft hyphen / replacement char issues
    ('\ufffd\xad', '\u00ed'),   # í via replacement
    ('\ufffd\u00ad', '\u00ed'), # í
]

total = 0
for fname in all_files:
    with open(fname, encoding='utf-8', errors='replace') as f:
        text = f.read()
    original = text
    for bad, good in fixes:
        text = text.replace(bad, good)
    if text != original:
        with open(fname, 'w', encoding='utf-8') as f:
            f.write(text)
        print('Fixed:', os.path.basename(fname))
        total += 1
    else:
        print('No change:', os.path.basename(fname))
print(f'Total files fixed: {total}')
