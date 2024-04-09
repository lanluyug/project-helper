package kk.lanluyu.projecthelper.io;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FileHelper {


    /**
     * 将一个文件夹内的所有文件合并成一个文件
     */
    @Test
    public void combine_dir_files(){
        // 输出的新文件
        String outputPath = "E:\\data\\output.sql";
        // 需要合并的目录
        String inputDir = "E:\\data\\prod";
        // 目录中需要排除的文件名（不带路径）
        String name = FileUtil.getName(outputPath);
        String[] inputExcludeFileNames = {name, };
        List<String> excludeNames = ListUtil.of(inputExcludeFileNames);

        List<File> files = FileUtil.loopFiles(inputDir).stream()
                .filter(file -> !excludeNames.contains(file.getName()))
                .collect(Collectors.toList());

        File outputFile = new File(outputPath);
        int count = 0;
        for (File file : files) {
            log.info("开始第{}个文件的复制，文件名：{}", ++ count, file.getName());
            List<String> strings = FileUtil.readLines(file, StandardCharsets.UTF_8);
            FileUtil.writeLines(strings, outputFile, StandardCharsets.UTF_8, true);
            log.info("复制完成--------------------------------");
        }

    }
}
