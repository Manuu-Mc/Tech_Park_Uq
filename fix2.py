import glob

all_files = glob.glob('src/main/java/**/*.java', recursive=True)

# Sequences: bad (as seen in UTF-8 file) -> correct char
# U+00C3 + U+2030 (‰ = 0x89 in win1252) -> É (U+00C9)
# U+00C3 + U+0161 (š = 0x9A in win1252) -> Ú (U+00DA)
# U+00C3 + U+201C (" = 0x93 in win1252) -> Ó (U+00D3)  [already fixed]
# U+00C2 + U+00A1 -> ¡ [already in fix_encoding but wasn't catching, force again]
fixes = [
    ('\u00c3\u2030', '\u00c9'),   # É  (Ã + ‰)
    ('\u00c3\u0161', '\u00da'),   # Ú  (Ã + š)
    ('\u00c3\u201c', '\u00d3'),   # Ó  (Ã + ")
    ('\u00c2\u00a1', '\u00a1'),   # ¡  (Â + ¡)  -- ensure clean
]

total = 0
for fname in all_files:
    with open(fname, encoding='utf-8') as f:
        text = f.read()
    original = text
    for bad, good in fixes:
        text = text.replace(bad, good)
    if text != original:
        with open(fname, 'w', encoding='utf-8') as f:
            f.write(text)
        print('Fixed:', fname.split('\\')[-1].split('/')[-1])
        total += 1
print('Total:', total)
