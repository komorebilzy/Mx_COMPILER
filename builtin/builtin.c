#define bool _Bool

int printf(const char *pattern, ...);

void print(char *s) { printf("%s", s); }

void println(char *s) { printf("%s\n", s); }

void printInt(int x) { printf("%d", x); }

void printlnInt(int x) { printf("%d\n", x); }

char *getString() {
  char *s = malloc(1 << 8);
  scanf("%s", s);
  return s;
}

int getInt() {
  int x;
  scanf("%d", &x);
  return x;
}

char *toString(int x) {
  char *s = malloc(1 << 4);
  sprintf(s, "%d", x);
  return s;
}

int __string_length(char *s){
    return strlen(s);
}

char *__string_substring(char *s, int l, int r) {
  char *t = malloc(r - l + 1);
  for (int i = l; i < r; i++) t[i - l] = s[i];
  t[r - l] = '\0';
  return t;
}

int __string_parseInt(char *s) {
  int x;
  sscanf(s, "%d", &x);
  return x;
}

int __string_ord(char *s, int x) { return s[x]; }

char *__string_add(char *s, char *t) {
  char *p = malloc(strlen(s) + strlen(t) + 1);
  strcpy(p, s);
  strcat(p, t);
  return p;
}

bool __string_slt(char *s, char *t) { return strcmp(s, t) < 0; }

bool __string_sle(char *s, char *t) { return strcmp(s, t) <= 0; }

bool __string_sgt(char *s, char *t) { return strcmp(s, t) > 0; }

bool __string_sge(char *s, char *t) { return strcmp(s, t) >= 0; }

bool __string_eq(char *s, char *t) { return strcmp(s, t) == 0; }

bool __string_ne(char *s, char *t) { return strcmp(s, t) != 0; }

char *_malloc(int size) { return (char *)malloc(size); }