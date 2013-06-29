require 'rake/clean'
require 'fileutils'
require 'popen4'
require 'test/unit/assertions'

module Algs4
  BIN_DIR = File.expand_path "target/classes"
  def find_changed
    self::FILES.each do |src_file|
      out_file = src_file.sub(self::DIR, BIN_DIR).sub(/\.java$/,".class")
      if !File.exist?(out_file) || File.mtime(src_file) > File.mtime(out_file)
        out_dir = File.dirname(out_file)
        if self::Changed[out_dir]
          self::Changed[out_dir] << src_file
        else
          self::Changed[out_dir] = [src_file]
        end
      end
    end
  end

  def run(cmd)
    puts cmd
    POpen4::popen4(cmd) do |stdout, stderr|
      puts stdout.read.strip
      puts stderr.read.strip
    end
  end

  module Src
    extend ::Algs4  # include find_changed(), run() as singletons
    DIR   = File.expand_path "src"
    FILES = Dir["#{DIR}/*/*.java"]  # NOT recursive
    Changed = {}

    def self.build
      Changed.each do |out_dir, src_list|
        FileUtils.mkdir_p out_dir, :verbose => false
        puts "Compile:"
        run "time javac -d #{out_dir} #{src_list.join(' ')}"

        jar_file   = "#{File.dirname(src_list.first)}.jar"
        class_list = Dir["#{out_dir}/*.class"]
        puts "Package:"
        run "jar cf #{jar_file} #{class_list.join(' ')}"
      end
    end
    module Main
      module Java
        extend ::Algs4
        DIR   = File.expand_path "src/main/java"
        FILES = Dir["#{DIR}/**/*.java"]
        Changed = {}

        def self.build
          Changed.each do |out_dir, src_list|
            FileUtils.mkdir_p out_dir, :verbose => false
            run "time javac -d #{out_dir} #{src_list.join(' ')}"
          end
        end
      end
    end
  end
end

directory Algs4::BIN_DIR

# clobber and clean lists
CLEAN.include(FileList["#{Algs4::BIN_DIR}/**/*.class"])

desc "find changed src files and group by directory"
task :find_changed do
  Algs4::Src::Main::Java.find_changed
  Algs4::Src.find_changed
end

task :all do
  Algs4::Src::Main::Java.build
  Algs4::Src.build
end

task :all => [:check_classpath, :find_changed]
task :default => :all

desc "warn and exit if no classpath"
task :check_classpath do
  include Test::Unit::Assertions
  script =  "#{ENV['HOME']}/cs/algs4/scripts/set_classpath"
  File.read(script)[/CLASSPATH=".*\$HOME([^\"]+)/]
  cp = $1

  assert(cp && !cp.empty?, "bad read of script: #{script}")
  assert_match(ENV['CLASSPATH'], /#{Regexp.escape(cp)}/,  "class path not set correctly")
end
