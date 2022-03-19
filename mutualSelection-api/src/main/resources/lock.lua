if redis.call("get",KEYS[1])==ARG[1] then -- 执行命令：
    return redis.call("del",KEYS[1])
else
    return 0
end
