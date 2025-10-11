function convert(tag, timestamp, record)
    local log_line = record["log"]
    local ist_time = log_line:match("dateTime=(%d+%-%d+%-%d+ %d+:%d+:%d+)")
    if ist_time then
        local pattern = "(%d+)-(%d+)-(%d+) (%d+):(%d+):(%d+)"
        local d,m,y,h,min,s = ist_time:match(pattern)
        if d then
            -- convert IST -> UTC for Fluent Bit timestamp
            timestamp = os.time({
                year = tonumber(y),
                month = tonumber(m),
                day = tonumber(d),
                hour = tonumber(h),
                min = tonumber(min),
                sec = tonumber(s)
            }) - (5*3600 + 30*60)

            -- keep IST for JSON field
            record["date"] = ist_time
        end
    end
    return 1, timestamp, record
end
