import math
def dist(p1, p2):
    return math.sqrt((p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2)

def sort_d(points,d):
    return sorted(points, key=lambda p: p[d])

def cps(points, d):
    points = sort_d(points,1)
    min_found = False
    for i in range(len(points)):
        point1 = points[i]
        for point2 in points[i + 1: i + 6]:
            current_dist = dist(point1, point2)
            if (current_dist < d):
                d = current_dist
                closest_points = [point1, point2]
                min_found = True
    return [d, *closest_points] if min_found else -1

def cpsr(points):
    if len(points) is 2:
        return [dist(*points), *points]

    inflectionPointIndex = len(points) // 2
    inflectionPoint = points[inflectionPointIndex]

    s1 = points[0:inflectionPointIndex]
    s2 = points[inflectionPointIndex:]

    s1_closest = cpsr(s1) if len(s1) > 1 else [float('inf'), *s1]
    s2_closest = cpsr(s2)

    d, p1, p2 = sorted([s1_closest, s2_closest], key=lambda s: s[0])[0]

    strip_points = list(filter(lambda point: abs(point[0] - inflectionPoint[0]) <= d, points))
    new_closest_pair = cps(strip_points, d)

    return [d, p1, p2] if new_closest_pair is -1 else new_closest_pair

if __name__ == "__main__":
    points=[]
    for n in range(int(input())):
        points.append(list(map(lambda x: int(x), input().split(" "))))
    points=sort_d(points, 0)
    print(cpsr(points)[0])
