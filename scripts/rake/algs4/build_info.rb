module Algs4
  class BuildInfo
    attr_reader :target_file
    def initialize(src_file, src_root, bin_root)
      @src_file     = src_file
      @target_root  = File.dirname(src_file).sub(src_root, bin_root)
      @package_name = get_package_name(src_file)
      @target_file  = compute_target_file
    end

    def target_dir_for_javac
      if @package_name
        parent_of_package
      else
        @target_root
      end
    end

    private
    def compute_target_file
      class_file = File.basename(@src_file).sub(/\.java$/, ".class")
      class_dir  = @target_root
      if (@package_name)
        class_dir = File.join(parent_of_package, @package_name.gsub(".", File::SEPARATOR))
      end
      File.join(class_dir, class_file)
    end

    def get_package_name(file)
      File.read(file)[/^\s*package\s+([^;]+)\s*/]
      $1
    end

    def parent_of_package
      unless @dir
        package_root = @package_name.split(".").first
        @target_root =~ /(.+)\/#{package_root}/
        @dir = $1 || @target_root
      end
      @dir
    end
  end
end
