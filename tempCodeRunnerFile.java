if (a[i] != 0 && prev != -1) {
            if (Math.abs(a[i] - prev) > 1) {
                return 0;
            }
        }