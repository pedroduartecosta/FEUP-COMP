int main() {
    #pragma tuner explore STEP(1, 10) reference(STEP=1)
    for (int i = 0; i < N; i += STEP) {
        acc += buf[i];
    }
    acc *= STEP;
    #pragma tuner max_abs_error acc 5
}
