clang-15 -S test.ll --target=riscv32-unknown-elf -o test.s.source -m32

sed 's/@plt$//g' test.s.source > test.s

rm -r -f test.s.source

ravel --input-file=test.in --output-file=test.out test.s builtin.s