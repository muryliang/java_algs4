#include <stdio.h>
#include <unistd.h>
#include <sys/time.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <time.h>

#define MAX 1000000

int do_threesum(int *p, int count);
double diff_time(struct timeval a, struct timeval b);

int cmp(const void *a, const void *b)
{
    int *m = (int*)a;
    int *n = (int*)b;

    if (m < n) return -1;
    else if (m > n) return 1;
    else return 0;
}
int main(void) {
    int res, N = 250, i;
    int *ptr;
    struct timeval prev, cur;

    srand(time(0));

    for (int i = 250; ; i += i) {
        ptr = malloc(i * sizeof(int));
        if (!ptr) {
            perror("error alloc ptr");
            exit(1);
        }
        for (int j = 0; j < i; j ++)
            ptr[j] = rand()%(MAX<<1) - MAX;
        qsort(ptr, i, sizeof(int), cmp);
        gettimeofday(&prev, NULL);
        res = do_threesum(ptr, i);
        gettimeofday(&cur, NULL);
        printf("cnt %d, time %5.5f\n", res, diff_time(prev, cur));
        free(ptr);
    }
    return 0;
}


int do_threesum(int *ptr, int count) {
    int min, max, cnt = 0;


//    for (int i = 0; i < count; i++) 
 //       printf("%d ", ptr[i]);
    for (int i = 0; i < count; i++) {
        min = i+1;
        max = count -1;
        while (ptr[i] + ptr[min] < 0 && min < max) {
            if (ptr[i] + ptr[min] + ptr[max] < 0)
                min ++;
            else if (ptr[i] + ptr[min] + ptr[max] > 0)
                max --;
            else  {
                    min ++; 
                    max --;
                    cnt++;
            }
        }
    }
    return cnt;
}
    
double diff_time(struct timeval a, struct timeval b) {
    double sm = b.tv_usec - a.tv_usec;
    double bg = b.tv_sec - a.tv_sec;

    if (sm < 0) {
        sm += 1000000;
        bg -= 1;
    }
    return bg + sm / 1000000.0;
}
