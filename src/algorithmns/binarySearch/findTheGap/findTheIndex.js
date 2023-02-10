const findTheIndex = (arr) => {
  const NUM = 32768;
  const helper = (arr, l, r) => {
    if (l === r) return l;
    const mid = ((r - l) >> 1) + l;
    if (arr[mid] <= NUM) {
      if (arr[mid + 1] > NUM) {
        return mid;
      } else {
        return helper(arr, mid + 1, r);
      }
    } else {
      return helper(arr, l, mid - 1);
    }
  };
  return helper(arr, 0, arr.length - 1);
};

const testarr1 = [231, 32768, 32769];
const testarr2 = [231, 2, 5, 40000];

console.log(findTheGap(testarr1)); // 1
console.log(findTheGap(testarr2)); // 2
