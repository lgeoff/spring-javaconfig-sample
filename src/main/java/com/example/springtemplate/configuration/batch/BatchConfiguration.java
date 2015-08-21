package com.example.springtemplate.configuration.batch;

import com.example.springtemplate.models.Book;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.util.logging.Logger;

/**
 * Created by lgeoff on 11.08.2015.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    Logger logger = Logger.getLogger(BatchConfiguration.class.getName());
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "csvJob")
    public Job csvJob(
            JobBuilderFactory jobs,
            @Qualifier("step1") Step s1
    ) {
        return jobs.get("csvJob").incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
    }

    @Bean(name = "step1")
    public Step step1(StepBuilderFactory stepBuilderFactory,
                      @Qualifier("jpaBookReader") ItemReader ir,
                      @Qualifier("csvBookWriter") ItemWriter iw,
                      @Qualifier("bookProcessor") ItemProcessor pr
    ) {
        return stepBuilderFactory.get("step1")
                .chunk(10)
                .reader(ir)
                .processor(pr)
                .writer(iw)
                .build();
    }

    @Bean(name = "jpaBookReader")
    public ItemReader<Book> jpaBookReader(EntityManagerFactory entityManagerFactory) {
        JpaPagingItemReader<Book> itemReader = new JpaPagingItemReader<Book>();
        itemReader.setEntityManagerFactory(entityManagerFactory);
        itemReader.setQueryString("from Book");
        itemReader.setPageSize(1000);
        return itemReader;
    }

    @Bean(name = "csvBookWriter")
    public ItemWriter<String> writer() {
        FlatFileItemWriter<String> writer = new FlatFileItemWriter<String>();
        writer.setResource(new FileSystemResource(new File("C:\\test.txt")));
        writer.setLineAggregator(new PassThroughLineAggregator<String>());
        return writer;
    }

    @Bean(name = "bookProcessor")
    public ItemProcessor<Book, String> processor() {
        return new ItemProcessor<Book, String>() {
            @Override
            public String process(Book book) throws Exception {
                logger.info("Returning " + book.toString());
                return book.toString();
            }
        };
    }

}
