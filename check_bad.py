import glob, os

all_files = glob.glob('src/main/java/**/*.java', recursive=True)

for fname in all_files:
    with open(fname, encoding='utf-8') as f:
        lines = f.readlines()
    for i, line in enumerate(lines, 1):
        bad_chars = [(j, ord(c), c) for j, c in enumerate(line) if ord(c) > 127]
        if bad_chars:
            # Print lines that have suspicious double-encoded patterns
            suspicious = [x for x in bad_chars if 0x00C0 <= x[1] <= 0x00C5 or x[1] in (0x00C2,) ]
            if suspicious:
                name = os.path.basename(fname)
                print(f'{name}:{i}: {repr(line.strip()[:100])}')
                for pos, cp, c in bad_chars:
                    print(f'  [{pos}] U+{cp:04X} {repr(c)}')
