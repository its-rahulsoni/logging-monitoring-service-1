function convert(tag, timestamp, record)
    local log_line = record["log"]

    -- Try to extract IST dateTime from log line (e.g. dateTime=11-10-2025 20:40:21)
    local ist_time = log_line:match("dateTime=(%d+%-%d+%-%d+ %d+:%d+:%d+)")

    if ist_time then
        -- Parse IST timestamp (day-month-year hour:minute:second)
        local d, m, y, h, min, s = ist_time:match("(%d+)%-(%d+)%-(%d+) (%d+):(%d+):(%d+)")
        if d then
            -- Convert IST → UTC for Fluent Bit internal timestamp
            timestamp = os.time({
                year  = tonumber(y),
                month = tonumber(m),
                day   = tonumber(d),
                hour  = tonumber(h),
                min   = tonumber(min),
                sec   = tonumber(s)
            }) - (5 * 3600 + 30 * 60)

            -- Keep IST as human-readable date in the record
            record["date"] = ist_time
        end
    else
        -- Logs without dateTime= (e.g. container/system logs)
        local ist_timestamp = timestamp + (5 * 3600 + 30 * 60)  -- UTC → IST
        local t = os.date("*t", ist_timestamp)
        record["date"] = string.format("%02d-%02d-%04d %02d:%02d:%02d",
                                       t.day, t.month, t.year, t.hour, t.min, t.sec)
    end

    return 1, timestamp, record
end
